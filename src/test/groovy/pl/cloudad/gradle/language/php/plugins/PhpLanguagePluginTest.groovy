package pl.cloudad.gradle.language.php.plugins

import org.gradle.api.Plugin
import org.gradle.language.base.plugins.LanguageBasePlugin
import org.gradle.language.base.plugins.LifecycleBasePlugin
import org.gradle.model.internal.type.ModelType
import org.gradle.platform.base.component.internal.ComponentSpecFactory
import org.gradle.platform.base.plugins.ComponentBasePlugin
import pl.cloudad.gradle.AbstractTest
import pl.cloudad.gradle.language.php.PhpSourceSet
import pl.cloudad.gradle.language.php.internal.DefaultPhpSourceSet

class PhpLanguagePluginTest extends AbstractTest
{
    def 'it applies language base plugin only'()
    {
        given:
        Class<Plugin> pluginClass = PhpLanguagePlugin

        when:
        dsl {
            apply plugin: pluginClass
        }

        then:
        with(pluginManager.pluginContainer) {
            size() == 4
            findPlugin(PhpLanguagePlugin) != null
            findPlugin(LanguageBasePlugin) != null
            findPlugin(ComponentBasePlugin) != null
            findPlugin(LifecycleBasePlugin) != null
        }
    }

    def 'it registers php source set with default implementation'()
    {
        given:
        Class<Plugin> pluginClass = PhpLanguagePlugin

        when:
        dsl {
            apply plugin: pluginClass
        }

        then:
        ModelType<PhpSourceSet> modelType = ModelType.of(PhpSourceSet)
        ModelType<DefaultPhpSourceSet> delegateType = ModelType.of(DefaultPhpSourceSet)

        with(project.modelRegistry.find('componentSpecFactory', ComponentSpecFactory)) {
            supportedTypes.find { it == modelType } != null
            getImplementationInfo(modelType).delegateType == delegateType
        }
    }
}
