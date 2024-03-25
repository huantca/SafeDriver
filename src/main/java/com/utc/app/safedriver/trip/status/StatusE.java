package com.utc.app.safedriver.trip.status;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusE {
    WAIT,
    CONFIRM,
    MOVING,
    CANCEL,
    COMPLETE
}
