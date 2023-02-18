package com.senex.notetaker.mvi

/**
 * Implementation of [TeaNext]<[State], [Command], [News]>
 */
internal class TeaNextImpl<out State, out Command, out News>(
    override val state: State?,
    override val commands: List<Command>,
    override val news: List<News>,
) : TeaNext<State, Command, News>
