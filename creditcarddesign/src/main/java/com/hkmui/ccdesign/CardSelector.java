package com.hkmui.ccdesign;

import com.hkmui.ccdesign.tool.CardType;

/**
 * Created by Harish on 01/01/16.
 * Edited by Hesk 12/12/2016
 */
public class CardSelector {
    private int mResCardId;
    private int mResChipOuterId;
    private int mResChipInnerId;
    private int mResCenterImageId;
    private int mResLogoId;

    public CardSelector(int mDrawableCard, int mDrawableChipOuter, int mDrawableChipInner, int mDrawableCenterImage, int logoId) {
        this.mResCardId = mDrawableCard;
        this.mResChipOuterId = mDrawableChipOuter;
        this.mResChipInnerId = mDrawableChipInner;
        this.mResCenterImageId = mDrawableCenterImage;
        this.mResLogoId = logoId;
    }

    public CardSelector(int mDrawableCard, int mDrawableChipOuter, int mDrawableChipInner, int logoId) {
        this(mDrawableCard, mDrawableChipOuter, mDrawableChipInner, android.R.color.transparent, logoId);
    }

    public int getResCardId() {
        return mResCardId;
    }

    public void setResCardId(int mResCardId) {
        this.mResCardId = mResCardId;
    }

    public int getResChipOuterId() {
        return mResChipOuterId;
    }

    public void setResChipOuterId(int mResChipOuterId) {
        this.mResChipOuterId = mResChipOuterId;
    }

    public int getResChipInnerId() {
        return mResChipInnerId;
    }

    public void setResChipInnerId(int mResChipInnerId) {
        this.mResChipInnerId = mResChipInnerId;
    }

    public int getResCenterImageId() {
        return mResCenterImageId;
    }

    public void setResCenterImageId(int mResCenterImageId) {
        this.mResCenterImageId = mResCenterImageId;
    }

    public int getResLogoId() {
        return mResLogoId;
    }

    public void setResLogoId(int mResLogoId) {
        this.mResLogoId = mResLogoId;
    }


    public static CardType selectCard(char cardFirstChar) {
        switch (cardFirstChar) {
            case '4':
                return CardType.VISA;
            case '5':
                return CardType.MASTER;
            case '3':
                return CardType.AMEX;
            default:
                return CardType.DEFAULT;
        }
    }

    /*
        if (cardNumber != null && cardNumber.length() >= 3) {
            CardSelector selector = selectCard(cardNumber.charAt(0));
            if (cardNumber.startsWith(AMEX_PREFIX)) {
                return AMEX;
            }
            if (selector != DEFAULT) {
                int[] drawables = {R.drawable.card_color_round_rect_brown, R.drawable.card_color_round_rect_green, R.drawable.card_color_round_rect_pink, R.drawable.card_color_round_rect_purple, R.drawable.card_color_round_rect_blue};
                int hash = cardNumber.substring(0, 3).hashCode();
                if (hash < 0) {
                    hash = hash * -1;
                }
                int index = hash % drawables.length;
                int chipIndex = hash % 3;
                int[] chipOuter = {R.drawable.chip, R.drawable.chip_yellow, android.R.color.transparent};
                int[] chipInner = {R.drawable.chip_inner, R.drawable.chip_yellow_inner, android.R.color.transparent};
                selector.setResCardId(drawables[index]);
                selector.setResChipOuterId(chipOuter[chipIndex]);
                selector.setResChipInnerId(chipInner[chipIndex]);
                return selector;
            }
        }*/


    public static CardType selectCard(String cardNumber) {
        if (CardType.VISA.isMatched(cardNumber)) {
            return CardType.VISA;
        } else if (CardType.MASTER.isMatched(cardNumber)) {
            return CardType.MASTER;
        } else if (CardType.AMEX.isMatched(cardNumber)) {
            return CardType.AMEX;
        } else if (CardType.DINERCLUB.isMatched(cardNumber)) {
            return CardType.DINERCLUB;
        } else if (CardType.DISC.isMatched(cardNumber)) {
            return CardType.DISC;
        } else if (CardType.JCB.isMatched(cardNumber)) {
            return CardType.JCB;
        } else if (CardType.UNIONPAY.isMatched(cardNumber)) {
            return CardType.UNIONPAY;
        } else if (CardType.BITCOIN.isMatched(cardNumber)) {
            return CardType.BITCOIN;
        } else if (CardType.MAESTRO.isMatched(cardNumber)) {
            return CardType.MAESTRO;
        } else {
            return CardType.DEFAULT;
        }
    }
}
