package mumble.mburger.sdk.MBClient.MBData.MBElements;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBAnswer;

/**
 * Identifies an array of answers for a poll.
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBPollAnswers extends MBClass {

    /**
     * Array of {@link mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBAnswer MBAnswer}
     */
    private ArrayList<MBAnswer> answers;

    /**
     * Time the poll ends
     */
    private long ends_at;

    /**
     * If you have voted, index of the answer
     */
    private int index_answer = -1;

    @Override
    public void initialize(long id, String name, String type) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
    }

    public MBPollAnswers(long id, String name, ArrayList<MBAnswer> answers, long ends_at, int index_answer) {
        this.answers = answers;
        this.ends_at = ends_at;
        this.index_answer = index_answer;
        initialize(id, name, MBConstants.type_poll);
    }

    /**
     * Get the array of {@link MBAnswer MBAnswer}
     */
    public ArrayList<MBAnswer> getAnswers() {
        return answers;
    }

    /**
     * Sets the array of {@link MBAnswer MBAnswer}
     */
    public void setAnswers(ArrayList<MBAnswer> answers) {
        this.answers = answers;
    }

    /**
     * Get when the poll ends (seconds)
     */
    public long getEnds_at() {
        return ends_at;
    }

    /**
     * Get when the poll ends (Milliseconds)
     */
    public long getEnds_atMillis() {
        return TimeUnit.SECONDS.toMillis(ends_at);
    }

    /**
     * Sets when the poll ends
     */
    public void setEnds_at(long ends_at) {
        this.ends_at = ends_at;
    }

    /**
     * Gets the index of the personal answer
     */
    public int getIndex_answer() {
        return index_answer;
    }

    /**
     * Sets when the index of the personal answer
     */
    public void setIndex_answer(int index_answer) {
        this.index_answer = index_answer;
    }
}
