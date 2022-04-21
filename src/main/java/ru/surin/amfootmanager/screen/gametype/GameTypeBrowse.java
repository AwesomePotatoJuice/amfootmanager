package ru.surin.amfootmanager.screen.gametype;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.GameType;

@UiController("afm_GameType.browse")
@UiDescriptor("game-type-browse.xml")
@LookupComponent("gameTypesTable")
public class GameTypeBrowse extends StandardLookup<GameType> {
}