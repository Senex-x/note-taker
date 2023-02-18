package com.senex.notetaker.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Implementation of [TeaStore]<[UIState], [UiEvent], [News]>
 */
internal class TeaStoreImpl<State, UIState, Event, UiEvent : Event, Command, News>(
    initialState: State,
    initialEvents: List<Event>,
    update: TeaUpdate<State, Event, Command, News>,
    commandFlowHandler: TeaCommandHandler<Command, Event>,
    uiStateMapper: (State) -> UIState,
    coroutineScope: CoroutineScope,
) : TeaStore<UIState, UiEvent, News> {

    private val stateFlow: MutableStateFlow<State> = MutableStateFlow(initialState)

    private val eventChannel = Channel<Event>(capacity = Channel.UNLIMITED)
    private val commandChannel = Channel<Command>(capacity = Channel.UNLIMITED)
    private val newsChannel = Channel<News>(capacity = Channel.UNLIMITED)

    override val newsFlow: Flow<News> = newsChannel.receiveAsFlow()

    override val uiStateFlow: MutableStateFlow<UIState> = MutableStateFlow(uiStateMapper(initialState))

    init {
        coroutineScope.launch {

            while (isActive) {

                val event = eventChannel.receive()
                val next = update(stateFlow.value, event)
                val newState = next.state

                if (newState != null) {
                    stateFlow.value = newState
                    uiStateFlow.value = uiStateMapper(newState)
                }

                for (command in next.commands) {
                    commandChannel.send(command)
                }

                for (news in next.news) {
                    newsChannel.send(news)
                }
            }
        }

        coroutineScope.launch {
            val commandsFlow = commandChannel.consumeAsFlow()
            commandFlowHandler(commandsFlow).collect(eventChannel::send)
        }

        coroutineScope.launch {
            initialEvents.forEach { event ->
                eventChannel.send(event)
            }
        }
    }

    override fun dispatch(event: UiEvent) {
        eventChannel.trySend(element = event)
    }
}
