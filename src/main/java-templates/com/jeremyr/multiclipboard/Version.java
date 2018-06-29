package com.jeremyr.multiclipboard;

public final class Version {

    private static final String VERSION = "${project.version}";
    private static final String GROUPID = "${project.groupId}";
    private static final String ARTIFACTID = "${project.artifactId}";
    private static final String TYPE = "${type}";
    private static final String GIT = "${project.scm.connection}";
    private static final String GIT_BRANCH = "${scmBranch}";
    private static final String REVISION = "${buildNumber}";

    public static String getVersion() {
        return VERSION;
    }

    public static String getArtifactId() {
        return ARTIFACTID;
    }

    public static String getType() {
      return TYPE;
    }

    public static String getGroupId() {
        return GROUPID;
    }

    public static String getGIT() {
        return GIT;
    }

    public static String getRevision() {
        return REVISION;
    }

    public static String getGITBranch() {
        return GIT_BRANCH;
    }
}
