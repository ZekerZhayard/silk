/*
 * Copyright 2016 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.loader.impl.game;

import java.nio.file.Path;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.util.CheckClassAdapter;
import org.sat4j.pb.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.spongepowered.asm.launch.MixinBootstrap;

import net.fabricmc.accesswidener.AccessWidener;
import net.fabricmc.loader.impl.util.UrlUtil;
import net.fabricmc.mapping.tree.TinyMappingFactory;
import net.fabricmc.tinyremapper.TinyRemapper;

enum LoaderLibrary {
	FABRIC_LOADER(UrlUtil.LOADER_CODE_SOURCE),
	TINY_MAPPINGS_PARSER(TinyMappingFactory.class),
	SPONGE_MIXIN(MixinBootstrap.class),
	TINY_REMAPPER(TinyRemapper.class),
	ACCESS_WIDENER(AccessWidener.class),
	ASM(ClassReader.class),
	ASM_ANALYSIS(Analyzer.class),
	ASM_COMMONS(Remapper.class),
	ASM_TREE(ClassNode.class),
	ASM_UTIL(CheckClassAdapter.class),
	SAT4J_CORE(ContradictionException.class),
	SAT4J_PB(SolverFactory.class);

	final Path path;

	LoaderLibrary(Class<?> cls) {
		this(UrlUtil.getCodeSource(cls));
	}

	LoaderLibrary(Path path) {
		if (path == null) throw new RuntimeException("missing loader library "+name());

		this.path = path;
	}
}
