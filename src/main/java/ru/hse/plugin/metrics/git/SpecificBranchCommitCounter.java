package ru.hse.plugin.metrics.git;

import org.jetbrains.annotations.NotNull;
import ru.hse.plugin.metrics.abstracts.CountingMetric;

import java.util.Objects;

import static ru.hse.plugin.metrics.commons.Names.SPECIFIC_BRANCH_COMMIT_COUNTER;

public class SpecificBranchCommitCounter extends CountingMetric {
    @NotNull
    private final String branchName;

    @NotNull
    @Override
    protected String getClassName() {
        return SPECIFIC_BRANCH_COMMIT_COUNTER;
    }

    public SpecificBranchCommitCounter(@NotNull String branchName) {
        super();
        this.branchName = branchName;
    }

    public SpecificBranchCommitCounter(int counter, @NotNull String branchName) {
        super(counter);
        this.branchName = branchName;
    }

    @Override
    public void justCommitted(String branchName) {
        if (this.branchName.equals(branchName)) {
            inc();
        }
    }

    @Override
    public @NotNull String getName() {
        return getClassName() + "()";
    }

    @Override
    public @NotNull String localStatisticString() {
        return "Number of commits on branch " + branchName;
    }

    @Override
    public String toString() {
        return getClassName() + " " + getCounter() + " " + branchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpecificBranchCommitCounter that = (SpecificBranchCommitCounter) o;
        return branchName.equals(that.branchName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), branchName);
    }
}
