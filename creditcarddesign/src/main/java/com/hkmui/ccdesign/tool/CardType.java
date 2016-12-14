package com.hkmui.ccdesign.tool;

import android.support.design.widget.Snackbar;

import com.hkmui.ccdesign.CardSelector;
import com.hkmui.ccdesign.R;

/**
 * Created by hesk on 16年12月12日.
 */
public enum CardType {
    VISA(
            "^4\\d{15}$",
            new CardSelector(R.drawable.card_color_round_rect_purple, R.drawable.chip, R.drawable.chip_inner, R.drawable.ic_billing_visa_logo)
    ),
    MASTER(
            "^5[1-5]\\d{14}$",
            new CardSelector(R.drawable.card_color_round_rect_pink, R.drawable.chip_yellow, R.drawable.chip_yellow_inner, R.drawable.ic_billing_mastercard_logo)
    ),
    AMEX(
            "^3[47]\\d{13}$",
            new CardSelector(R.drawable.card_color_round_rect_green, android.R.color.transparent, android.R.color.transparent, R.drawable.img_amex_center_face, R.drawable.ic_billing_amex)
    ),
    UNIONPAY(
            "^62[0-5]\\d{13,16}$",
            new CardSelector(R.drawable.card_color_round_rect_default, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_billing_unionpay)
    ),
    DISC(
            "^6(?:011\\d\\d|5\\d{4}|4[4-9]\\d{3}|22(?:1(?:2[6-9]|[3-9]\\d)|[2-8]\\d\\d|9(?:[01]\\d|2[0-5])))\\d{10}$",
            new CardSelector(R.drawable.card_color_round_rect_default, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_billing_discover)

    ),
    JCB(
            "^35(?:2[89]|[3-8]\\d)\\d{12}$",
            new CardSelector(R.drawable.card_color_round_rect_default, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_billing_jcb)
    ),
    MAESTRO(
            "^(?:5018|5020|5038|5612|5893|6304|6759|6761|6762|6763|0604|6390)[0-9]{8,15}$",
            new CardSelector(R.drawable.card_color_round_rect_default, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_billing_mastro)
    ),
    DINERCLUB(
            "^(?:5018|5020|5038|5612|5893|6304|6759|6761|6762|6763|0604|6390)[0-9]{8,15}$",
            new CardSelector(R.drawable.card_color_round_rect_default, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_billing_diners_club)
    ),
    BITCOIN(
            "^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$",
            new CardSelector(R.drawable.card_color_round_rect_default, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_bitcoin_ga, R.drawable.ic_billing_bitcoin)
    ),
    DEFAULT("",
            new CardSelector(R.drawable.card_color_round_rect_default, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent)
    );
    private String pattern;
    private CardSelector cardFace;

    CardType(String pattern, CardSelector cardface) {
        this.pattern = pattern;
        this.cardFace = cardface;
    }

    public boolean isMatched(String card_number) {
        return card_number.matches(this.pattern);
    }

    public CardSelector getCardFace() {
        return cardFace;
    }


    public static int getCardTypeImageByCardNum(String cardNumber) {
        if (CardType.AMEX.isMatched(cardNumber)) {
            return CardType.AMEX.getCardFace().getResLogoId();
        } else if (CardType.VISA.isMatched(cardNumber)) {
            return CardType.VISA.getCardFace().getResLogoId();
        } else if (CardType.MASTER.isMatched(cardNumber)) {
            return CardType.MASTER.getCardFace().getResLogoId();
        } else if (CardType.UNIONPAY.isMatched(cardNumber)) {
            return CardType.UNIONPAY.getCardFace().getResLogoId();
        } else if (CardType.DISC.isMatched(cardNumber)) {
            return CardType.DISC.getCardFace().getResLogoId();
        } else if (CardType.JCB.isMatched(cardNumber)) {
            return CardType.JCB.getCardFace().getResLogoId();
        } else if (CardType.BITCOIN.isMatched(cardNumber)) {
            return CardType.BITCOIN.getCardFace().getResLogoId();
        } else {
            return 0;
        }
    }
}
