package com.example.assignment1;

import javafx.scene.control.ListCell;

public class PaletteCell extends ListCell<ColorPalette> {

    @Override
    protected void updateItem(ColorPalette colorPalette, boolean empty) {
        super.updateItem(colorPalette, empty);
        PaletteView pv = new PaletteView(colorPalette);
        setGraphic(pv);
    }
}
