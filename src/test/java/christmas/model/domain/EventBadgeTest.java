package christmas.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBadgeTest {
    @Nested
    class SuccessTest {
        @DisplayName("금액에 따라 배지를 차등 지급한다")
        @Test
        void should_awardBadge_ByBenefitAmount() {
            // when & then
            assertThat(EventBadge.of(1000)).isEqualTo(null);
            assertThat(EventBadge.of(6000)).isEqualTo(EventBadge.STAR);
            assertThat(EventBadge.of(15000)).isEqualTo(EventBadge.TREE);
            assertThat(EventBadge.of(25000)).isEqualTo(EventBadge.SANTA);
        }
    }
}
