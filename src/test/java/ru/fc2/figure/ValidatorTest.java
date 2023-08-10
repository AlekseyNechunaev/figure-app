package ru.fc2.figure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fc2.figure.utils.validation.CircleValidator;
import ru.fc2.figure.utils.validation.FigureValidator;
import ru.fc2.figure.utils.validation.RectangleValidator;
import ru.fc2.figure.utils.validation.TriangleValidator;

public class ValidatorTest {
    private static final FigureValidator CIRCLE_VALIDATOR = new CircleValidator();
    private static final FigureValidator TRIANGLE_VALIDATOR = new TriangleValidator();
    private static final FigureValidator RECTANGLE_VALIDATOR = new RectangleValidator();

    @Test
    void circleValidatorTest() {
        Assertions.assertThat(CIRCLE_VALIDATOR.isValidParameters(new Double[0])).isFalse();
        Assertions.assertThat(CIRCLE_VALIDATOR.isValidParameters(new Double[]{0d})).isFalse();
        Assertions.assertThat(CIRCLE_VALIDATOR.isValidParameters(new Double[]{5d, 6d})).isFalse();
        Assertions.assertThat(CIRCLE_VALIDATOR.isValidParameters(new Double[]{5d})).isTrue();
    }

    @Test
    void triangleValidatorTest() {
        Assertions.assertThat(TRIANGLE_VALIDATOR.isValidParameters(new Double[0])).isFalse();
        Assertions.assertThat(TRIANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 2d})).isFalse();
        Assertions.assertThat(TRIANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 2d, 3d, 4d})).isFalse();
        Assertions.assertThat(TRIANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 2d, 0d})).isFalse();
        Assertions.assertThat(TRIANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 2d, 3d})).isTrue();
    }

    @Test
    void rectangleValidator() {
        Assertions.assertThat(RECTANGLE_VALIDATOR.isValidParameters(new Double[0])).isFalse();
        Assertions.assertThat(RECTANGLE_VALIDATOR.isValidParameters(new Double[]{1d})).isFalse();
        Assertions.assertThat(RECTANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 2d, 3d})).isFalse();
        Assertions.assertThat(RECTANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 0d})).isFalse();
        Assertions.assertThat(RECTANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 1d})).isFalse();
        Assertions.assertThat(RECTANGLE_VALIDATOR.isValidParameters(new Double[]{1d, 2d})).isTrue();
    }
}
