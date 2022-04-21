package ru.surin.amfootmanager.screen.playercard;

import io.jmix.ui.screen.*;
import ru.surin.amfootmanager.entity.PlayerCard;

@UiController("afm_PlayerCard.edit")
@UiDescriptor("player-card-edit.xml")
@EditedEntityContainer("playerCardDc")
public class PlayerCardEdit extends StandardEditor<PlayerCard> {
}