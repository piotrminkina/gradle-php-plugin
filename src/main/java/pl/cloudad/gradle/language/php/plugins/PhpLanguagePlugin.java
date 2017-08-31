package pl.cloudad.gradle.language.php.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.language.base.plugins.LanguageBasePlugin;
import org.gradle.model.RuleSource;
import org.gradle.platform.base.ComponentType;
import org.gradle.platform.base.TypeBuilder;
import pl.cloudad.gradle.language.php.PhpSourceSet;
import pl.cloudad.gradle.language.php.internal.DefaultPhpSourceSet;

public class PhpLanguagePlugin implements Plugin<Project>
{
    @Override
    public void apply(Project project)
    {
        project.getPluginManager().apply(LanguageBasePlugin.class);
    }

    @SuppressWarnings("UnusedDeclaration")
    static class PluginRules extends RuleSource
    {
        @ComponentType
        void registerPhpLanguage(TypeBuilder<PhpSourceSet> builder)
        {
            builder.defaultImplementation(DefaultPhpSourceSet.class);
        }
    }
}
