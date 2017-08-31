package pl.cloudad.gradle

import org.gradle.api.Project
import org.gradle.api.internal.plugins.PluginManagerInternal
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

abstract class AbstractTest extends Specification
{
    File testProjectDir
    ProjectInternal project

    def setup()
    {
        testProjectDir = File.createTempDir('gradle', 'projectDir')
        project = (ProjectInternal)ProjectBuilder.builder().withProjectDir(testProjectDir).build()
    }

    def cleanup()
    {
        testProjectDir.deleteDir()
    }

    void dsl(
            @DelegatesTo(value = Project, strategy = Closure.DELEGATE_FIRST) Closure body
    ) {
        body.delegate = project
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.call()

        project.bindAllModelRules()
    }

    protected PluginManagerInternal getPluginManager()
    {
        return (PluginManagerInternal)project.pluginManager
    }
}
