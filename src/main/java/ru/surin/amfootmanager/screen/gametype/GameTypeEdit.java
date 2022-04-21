package ru.surin.amfootmanager.screen.gametype;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.GameType;

@UiController("afm_GameType.edit")
@UiDescriptor("game-type-edit.xml")
@EditedEntityContainer("gameTypeDc")
public class GameTypeEdit extends StandardEditor<GameType> {
}