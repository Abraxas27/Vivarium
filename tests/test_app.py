import src.module.main as app


def test_basic():
    assert (
        app.function1() == "test OK"
    )


def test_fail():
    assert (
        app.function2() != "test OK"
    )
