package com.infilos.sampler.layout;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Provider for controls per {@link Column}.
 *
 * @param <T> Node type.
 */
public interface ColumnControlProvider<T extends Node> {
    /**
     * @return Label showing the {@code T} type.
     */
    Label key();

    /**
     * @param column Column to place control in.
     * @return Control instance for the column.
     */
    T create(Column column);
}

