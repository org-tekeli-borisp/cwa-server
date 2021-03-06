

package app.coronawarn.server.services.distribution.assembly.structure.directory;

import app.coronawarn.server.common.shared.collection.ImmutableStack;
import app.coronawarn.server.common.shared.functional.Formatter;
import app.coronawarn.server.services.distribution.assembly.structure.Writable;
import app.coronawarn.server.services.distribution.assembly.structure.util.functional.WritableFunction;
import java.util.Set;

/**
 * A "meta {@link Directory directory}" that maps its on-disk subdirectories to some list of elements. This list of
 * elements is determined by a {@link WritableFunction}.
 *
 * @param <W> The specific type of {@link Writable} that this {@link IndexDirectory} can be a child of.
 * @param <T> The type of the elements in the index.
 */
public interface IndexDirectory<T, W extends Writable<W>> extends Directory<W> {

  /**
   * Adds a writable under the name {@code name}, whose content is calculated by the {@code writableFunction} to each
   * one of the directories created from the index. The {@code fileFunction} calculates the file content from a {@link
   * ImmutableStack} of parent {@link IndexDirectoryOnDisk} indices. File content calculation happens on {@link
   * DirectoryOnDisk#write}.
   *
   * @param writableFunction A function that can output a new writable.
   */
  void addWritableToAll(WritableFunction<W> writableFunction);

  /**
   * Calls the {@link app.coronawarn.server.services.distribution.assembly.structure.util.functional.IndexFunction} with
   * the {@code indices} to calculate and return the elements of the index of this {@link IndexDirectory}.
   *
   * @param indices A {@link ImmutableStack} of parameters from all {@link IndexDirectory IndexDirectories} further up
   *                in the hierarchy. The Stack may contain different types, depending on the types {@code T} of {@link
   *                IndexDirectory IndexDirectories} further up in the hierarchy.
   * @return generic type Set generic type
   */
  Set<T> getIndex(ImmutableStack<Object> indices);

  /**
   * Returns the function used to format elements of the index (e.g. for writing to disk).
   *
   * @return generic type Formatter generic type
   */
  Formatter<T> getIndexFormatter();
}
