package testing.ecloud.helpers;

public enum TestRailStatus {
    PASSED(1), BLOCKED(2), UNTESTED(3), RETEST(4), FAILED(5);

    int statusId;

    TestRailStatus(int statusId) {
        this.statusId = statusId;
    }
}
