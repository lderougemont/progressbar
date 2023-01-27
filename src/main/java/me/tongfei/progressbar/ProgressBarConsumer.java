package me.tongfei.progressbar;

import java.util.function.Consumer;

import static me.tongfei.progressbar.TerminalUtils.CARRIAGE_RETURN;

/**
 * A consumer that prints a rendered progress bar.
 * @since 0.8.0
 * @author Alex Peelman
 * @author Tongfei Chen
 */
public interface ProgressBarConsumer extends Consumer<String>, Appendable, AutoCloseable {

    /**
     * Returns the maximum length allowed for the rendered form of a progress bar.
     */
    int getMaxRenderedLength();

    /**
     * Accepts a rendered form of a progress bar, e.g., prints to a specified stream.
     * @param rendered Rendered form of a progress bar, a string
     */
    void accept(String rendered);

    /** Clears the progress bar from the display. */
    default void clear() {
        accept(CARRIAGE_RETURN + Util.repeat(' ', getMaxRenderedLength()) + CARRIAGE_RETURN);
    }

    default ProgressBarConsumer append(CharSequence csq) {
        accept(csq.toString());
        return this;
    }

    default ProgressBarConsumer append(CharSequence csq, int start, int end) {
        accept(csq.subSequence(start, end).toString());
        return this;
    }

    default ProgressBarConsumer append(char c) {
        accept(String.valueOf(c));
        return this;
    }

    void close();

}
