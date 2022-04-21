package ru.surin.amfootmanager.screen.playercard;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.PlayerCard;

@UiController("afm_PlayerCard.browse")
@UiDescriptor("player-card-browse.xml")
@LookupComponent("playerCardsTable")
public class PlayerCardBrowse extends StandardLookup<PlayerCard> {
}