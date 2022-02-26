package com.sweetzpot.stravazpot.upload.model;

public enum UploadActivityType {
    RUN("run"),
    RIDE("ride"),
    WALK("walk"),
    HIKE("hike"),
    CANOEING("canoeing"),
    EBIKE_RIDE("ebikeride"),
    HANDCYCLE("handcycle"),
    VELOMOBILE("velomobile"),
    ICE_SKATE("iceskate"),
    INLINE_SKATE("inlineskate"),
    KAYAKING("kayaking"),
    KITESURF("kitesurf"),
    ROCK_CLIMBING("rockclimbing"),
    ROLLER_SKI("rollerski"),
    ROWING("rowing"),
    ALPINE_SKI("alpineski"),
    BACKCOUNTRY_SKI("backcountryski"),
    NORDIC_SKI("nordicski"),
    SNOWBOARD("snowboard"),
    SNOWSHOE("snowshoe"),
    STAND_UP_PADDLING("standuppaddling"),
    SURFING("surfing"),
    SWIM("swim"),
    WHEELCHAIR("wheelchair"),
    WINDSURF("windsurf"),
    CROSSFIT("crossfit"),
    ELLIPTICAL("elliptical"),
    STAIR_STEPPER("stairstepper"),
    WEIGHT_TRAINING("weighttraining"),
    YOGA("yoga"),
    WORKOUT("workout"),
    VIRTUAL_RIDE("virtualride");

    private String rawValue;

    UploadActivityType(String rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public String toString() {
        return rawValue;
    }
}
