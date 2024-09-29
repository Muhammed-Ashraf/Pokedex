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

@file:Suppress("SpellCheckingInspection")

package com.example.pokedex.compose.core.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
 * MainCoroutinesRule is a custom JUnit test rule that allows testing coroutines
 * that depend on the Dispatchers.Main dispatcher. In a typical Android environment,
 * Dispatchers.Main is tied to the Android main thread, which is not available in
 * unit tests. This rule replaces the main dispatcher with a test dispatcher, ensuring
 * the coroutines can be tested outside of the Android runtime.
 *
 * The class extends TestWatcher, which allows it to hook into the lifecycle of
 * JUnit tests, setting up and tearing down coroutine-related behavior before and
 * after each test.
 */
class MainCoroutinesRule(
  /*
   * testDispatcher is the dispatcher that will replace Dispatchers.Main during testing.
   * The default value is UnconfinedTestDispatcher(), which ensures that the coroutines
   * are executed immediately and are not confined to any specific thread.
   * This is useful for testing because it simplifies the execution of coroutines
   * without thread restrictions.
   */
  val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

  /*
   * testScope is a coroutine scope associated with the testDispatcher.
   * This scope is used to launch coroutines within the test environment.
   * Since it's tied to the testDispatcher, all coroutines launched in this scope
   * will be controlled by the test dispatcher, ensuring immediate execution.
   */
  val testScope = TestScope(testDispatcher)

  /*
   * The starting function is called before each test begins. This method overrides
   * the parent TestWatcher class's starting method.
   *
   * It replaces the standard Dispatchers.Main with the testDispatcher, ensuring that
   * any coroutine that relies on Dispatchers.Main will now use testDispatcher instead.
   * This allows the code to run in the test environment without relying on Android's
   * main thread.
   *
   * @param description provides information about the current test that is being executed.
   */
  override fun starting(description: Description) {
    // Replace Dispatchers.Main with the test dispatcher before the test begins
    Dispatchers.setMain(testDispatcher)
  }

  /*
   * The finished function is called after each test is completed. This method ensures
   * that the main dispatcher is reset to its original state after the test is done.
   * It calls Dispatchers.resetMain() to restore the original Dispatchers.Main and
   * also calls the parent class's finished method to perform any additional clean-up.
   *
   * This ensures that the test environment does not leak the test dispatcher into
   * other tests or real code after the test is completed.
   *
   * @param description provides information about the test that has just finished.
   */
  override fun finished(description: Description) {
    // Call the parent class's finished method for any additional teardown
    super.finished(description)
    // Reset Dispatchers.Main to its original state after the test
    Dispatchers.resetMain()
  }
}
