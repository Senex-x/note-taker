package com.senex.notetaker.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface supplied to the render layer, containing the [UIState] and [News] flows and handling [UiEvent]'s from the render layer
 *
 * [UIState] - an entity that represents a handy for rendering screen state
 * [UiEvent] - actions representing all events from rendering layer
 * [News] - actions representing one-time actions at the rendering layer
 */
interface TeaStore<out UIState, in UiEvent, out News> {

    val uiStateFlow: StateFlow<UIState>

    val newsFlow: Flow<News>

    fun dispatch(event: UiEvent)
}

/**
 * Function that handle actual State, new Event and can trigger new State, lists of News and Command's
 *
 * @param State - an entity representing the true state of the store
 * @param Event - actions triggering dynamic changes of the store
 * @param Command - actions triggering asynchronous requests
 * @param News - actions representing one-time actions on the rendering layer
 *
 * oldState - actual State of [TeaStore]
 * event - actions from rendering layer or [TeaCommandHandler]
 *
 * @return [TeaNext]<State, Command, News>
 */
typealias TeaUpdate<State, Event, Command, News> = (oldState: State, event: Event) -> TeaNext<State, Command, News>

/**
 * Interface represents the result of calling an [TeaUpdate] function
 *
 * @param State - an entity representing true state of store
 * @param Command - actions triggering asynchronous requests
 * @param News - actions representing one-time actions of the rendering layer
 */
interface TeaNext<out State, out Command, out News> {
    val state: State?
    val commands: List<Command>
    val news: List<News>
}

/**
 * Function to map Flow<Command> to Flow<Event> with results of asynchronous requests
 *
 * @param Command - actions triggering asynchronous requests
 * @param Event - actions representing results of asynchronous requests and triggering dynamic changes of the store by [TeaUpdate]
 *
 *  @return Event's flow
 */
typealias TeaCommandHandler<Command, Event> = (commandFlow: Flow<Command>) -> Flow<Event>

/**
 * Function to initialize [TeaStore]<[UIState], [UiEvent], [News]>
 *
 * @param State - an entity representing the true state of the store
 * @param UIState - an entity representing a handy for rendering screen state
 * @param Event - actions triggering dynamic changes of this [TeaStore]
 * @param UiEvent: [Event] - actions from rendering layer triggering dynamic changes of this [TeaStore]
 * @param Command - actions triggering asynchronous requests
 * @param News - actions representing one-time actions on the rendering layer
 *
 * [initialState] - start [State] of [TeaStore]
 * [initialEvents] - list of events to trigger at [TeaStore]'s init
 * [update] - [TeaUpdate]<[State], [Event], [Command], [News]> of this [TeaStore]
 * [commandFlowHandler] - [TeaCommandHandler]<[Command], [Event]> of this [TeaStore]
 * [uiStateMapper] - [State] to [UIState] mapper
 * [coroutineScope] - [CoroutineScope] for [TeaUpdate], [TeaCommandHandler] and [uiStateMapper] of this [TeaStore]. Use viewModelScope
 *
 * @return [TeaStore]<[UIState], [UiEvent], [News]>
 */
fun <State, UIState, Event, UiEvent : Event, Command, News> teaStore(
    initialState: State,
    initialEvents: List<Event>,
    update: TeaUpdate<State, Event, Command, News>,
    commandFlowHandler: TeaCommandHandler<Command, Event>,
    uiStateMapper: (State) -> UIState,
    coroutineScope: CoroutineScope,
): TeaStore<UIState, UiEvent, News> {
    return TeaStoreImpl(
        initialState = initialState,
        initialEvents = initialEvents,
        update = update,
        commandFlowHandler = commandFlowHandler,
        uiStateMapper = uiStateMapper,
        coroutineScope = coroutineScope,
    )
}

/**
 * Function to initialize [TeaStore]<[State], [Command], [News]>
 *
 * @param State - an entity representing true state of store
 * @param Command - actions triggering asynchronous requests
 * @param News - actions representing one-time actions of the rendering layer
 *
 * [state] - new [State] of [TeaStore]. Pass null if you don't need to change [State]
 *
 * @return [TeaStore]<[State], [Command], [News]>
 */
fun <State, Command, News> teaNext(
    state: State? = null,
    commands: List<Command> = emptyList(),
    news: List<News> = emptyList(),
): TeaNext<State, Command, News> {
    return TeaNextImpl(
        state = state,
        commands = commands,
        news = news,
    )
}
