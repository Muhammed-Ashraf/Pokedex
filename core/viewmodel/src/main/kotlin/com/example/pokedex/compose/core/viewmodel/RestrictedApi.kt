package com.example.pokedex.compose.core.viewmodel
/*
 * Designed and developed by 2024 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// This annotation marks APIs as "restricted," meaning their usage is discouraged for regular purposes.
// Such an API might be experimental, unstable, or have limited use cases. The annotation helps
// developers avoid unintended dependencies on this API by requiring explicit opt-in.
@Target(
    // Specifies that this annotation can be applied to:
    // - CLASS: to annotate entire classes
    // - PROPERTY: to annotate individual properties (fields)
    // - CONSTRUCTOR: to annotate constructors
    // - FUNCTION: to annotate functions or methods
    // - TYPEALIAS: to annotate type aliases (alternative names for types)
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS,
)
@Retention(AnnotationRetention.BINARY)
// AnnotationRetention.BINARY means the annotation is stored in the compiled class files,
// but it won’t be accessible at runtime. This retention strategy is useful for restricting
// API usage while keeping metadata about the restriction in the compiled files,
// but without runtime performance impact or reflection access.
@RequiresOptIn(
    // A custom message is provided to explain why this API is restricted. When a developer
    // tries to use this API without opting in, they’ll see this message in the IDE or during compilation.
    message = "This API has been restricted. Do not depend on this API for working properly",

    // The opt-in level is set to ERROR, meaning if a developer uses this API without explicitly
    // opting in, it will produce a compile-time error. This is the strictest opt-in level,
    // ensuring developers must acknowledge and allow the usage manually.
    level = RequiresOptIn.Level.ERROR,
)
// Declares the RestrictedApi annotation class as public, so it’s accessible outside this package.
public annotation class RestrictedApi

