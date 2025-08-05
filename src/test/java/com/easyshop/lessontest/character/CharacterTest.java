package com.easyshop.lessontest.character;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterTest {

    @Test
    @DisplayName("isDigit()은 char 타입의 문자가 숫자인지 확인한다")
    void isDigitTest() {
        assertThat(Character.isDigit('1')).isTrue();
        assertThat(Character.isDigit('a')).isFalse();
        assertThat(Character.isDigit(' ')).isFalse();
    }

    @Test
    @DisplayName("isLetter()은 char 타입의 문자가 알파벳인지 확인한다.")
    void isLetterTest() {
        assertThat(Character.isLetter('a')).isTrue();
        assertThat(Character.isLetter('Z')).isTrue();
        assertThat(Character.isLetter('%')).isFalse();
        assertThat(Character.isLetter(' ')).isFalse();
    }

    @Test
    @DisplayName("isWhitespace는 char 타입의 입력이 공백인지 확인한다.")
    void isWhitespaceTest() {
        assertThat(Character.isWhitespace(' ')).isTrue();
        assertThat(Character.isWhitespace('\n')).isTrue();
        assertThat(Character.isWhitespace('a')).isFalse();
    }

    @Test
    @DisplayName("대소문자 판별 및 변환")
    void shouldConvertCaseProperly() {
        assertThat(Character.isUpperCase('A')).isTrue();
        assertThat(Character.isLowerCase('a')).isTrue();

        assertThat(Character.toUpperCase('a')).isEqualTo('A');
        assertThat(Character.toLowerCase('A')).isEqualTo('a');
    }
}
