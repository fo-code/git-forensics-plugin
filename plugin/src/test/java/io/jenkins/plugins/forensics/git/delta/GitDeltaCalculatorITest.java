import org.junit.jupiter.api.Test;
class GitDeltaCalculatorITest extends GitITest {
    private static final String EMPTY_FILE_PATH = "";
    void shouldCreateEmptyDeltaIfCommitsAreInvalid() {
    void shouldCreateDiffFile() {
    void shouldDetermineAddedFile() {
        assertThat(fileChanges.getOldFileName()).isEqualTo(EMPTY_FILE_PATH);
    void shouldDetermineModifiedFile() {
        assertThat(fileChanges.getFileName()).isEqualTo(INITIAL_FILE);
        assertThat(fileChanges.getOldFileName()).isEqualTo(INITIAL_FILE);
    void shouldDetermineDeletedFile() {
        git("rm", INITIAL_FILE);
        assertThat(fileChanges.getFileName()).isEqualTo(EMPTY_FILE_PATH);
        assertThat(fileChanges.getOldFileName()).isEqualTo(INITIAL_FILE);
    /**
     * A renamed file should be determined.
     */
    @Test
    void shouldDetermineRenamedFile() {
        FreeStyleProject job = createJobWithReferenceRecorder();
        GitDeltaCalculator deltaCalculator = createDeltaCalculator();
        FilteredLog log = createLog();

        String content = "content";
        commitFile(content);
        Run<?, ?> referenceBuild = buildSuccessfully(job);
        git("rm", INITIAL_FILE);
        writeFileAsAuthorFoo(content);
        Run<?, ?> build = buildSuccessfully(job);

        Optional<Delta> result = deltaCalculator.calculateDelta(build, referenceBuild, EMPTY_SCM_KEY, log);
        assertThat(result).isNotEmpty();

        Delta delta = result.get();
        FileChanges fileChanges = getSingleFileChanges(delta);
        assertThat(fileChanges.getFileName()).isEqualTo(ADDITIONAL_FILE);
        assertThat(fileChanges.getOldFileName()).isEqualTo(INITIAL_FILE);
        assertThat(fileChanges.getFileEditType()).isEqualTo(FileEditType.RENAME);
        assertThat(fileChanges.getFileContent()).isEqualTo(content);
    }

    void shouldDetermineAddedLines() {
    void shouldDetermineModifiedLines() {
    void shouldDetermineDeletedLines() {
    void shouldDetermineAllChangeTypesTogether() {