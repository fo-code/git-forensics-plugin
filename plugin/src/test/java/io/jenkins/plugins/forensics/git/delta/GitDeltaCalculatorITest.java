import java.io.IOException;
import hudson.model.FreeStyleProject;
import hudson.model.Run;
import hudson.plugins.git.GitSCM;

import io.jenkins.plugins.forensics.git.reference.GitReferenceRecorder;
import static org.mockito.Mockito.*;
    private static final String EMPTY_SCM_KEY = "";

        assertThat(deltaCalculator.calculateDelta(mock(Run.class), mock(Run.class), EMPTY_SCM_KEY, log)).isEmpty();
        FreeStyleProject job = createJobWithReferenceRecorder();

        Run<?, ?> referenceBuild = buildSuccessfully(job);
        String referenceCommit = getHead();
        String fileName = "newFile";
        String content = "content";
        Run<?, ?> build = buildSuccessfully(job);
        String currentCommit = getHead();
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        String newFileName = "newFile";
        String content = "added";
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        String content = "modified";
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        String content = "content";
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        String content = "Test\nTest\n";
        String insertedContent = "Test\nInsert1\nInsert2\nTest\n";
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        String content = "Test\nTest\nTest\nTest";
        String modified = "Test\nModified\nModified2\nTest";
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        String content = "Test\nTest3\nTest";
        String modified = "Test\nTest";
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        FreeStyleProject job = createJobWithReferenceRecorder();
        String content = "Test1\nTest2\nTest3\nTest4";
        String newContent = "Modified\nTest2\nInserted\nTest3";
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        Run<?, ?> build = buildSuccessfully(job);
        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);

    /**
     * Creates a {@link FreeStyleProject} which contains a {@link GitReferenceRecorder} within the publishers list.
     *
     * @return the created project
     */
    private FreeStyleProject createJobWithReferenceRecorder() {
        try {
            FreeStyleProject job = createFreeStyleProject();
            job.setScm(new GitSCM(getRepositoryRoot()));
            job.getPublishersList().add(new GitReferenceRecorder());
            return job;
        }
        catch (IOException exception) {
            throw new AssertionError(exception);
        }
    }