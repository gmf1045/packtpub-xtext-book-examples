/*
 * generated by Xtext
 */
package org.example.entities;

import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.example.entities.generator.EntitiesOutputConfigurationProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class EntitiesRuntimeModule extends org.example.entities.AbstractEntitiesRuntimeModule {

    public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
        return EntitiesOutputConfigurationProvider.class;
    }

    // this is required only by the CompilationTestHelper since Xtext 2.7
    public Class<? extends org.eclipse.xtend.lib.macro.file.MutableFileSystemSupport> bindMutableFileSystemSupport() {
		return org.eclipse.xtext.xbase.file.JavaIOFileSystemSupport.class;
	}

    // this is required only by the CompilationTestHelper since Xtext 2.7
    public Class<? extends com.google.inject.Provider<org.eclipse.xtext.xbase.file.WorkspaceConfig>> provideWorkspaceConfig() {
		return org.eclipse.xtext.xbase.file.RuntimeWorkspaceConfigProvider.class;
	}
}
