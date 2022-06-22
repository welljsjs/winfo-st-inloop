import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class PredicateIterator<T> implements Iterator<T> {
  private Iterator<T> iter;
  private Predicate<T> predicate;
  private T possibleNext;

  public PredicateIterator(Iterator<T> iter, Predicate<T> predicate) {
    this.iter = Objects.requireNonNull(iter, "iter must be non-null");
    this.predicate = Objects.requireNonNull(predicate, "predicate must be non-null");
  }

  @Override
  public boolean hasNext() {
    if (possibleNext != null) // return true if we've previously found a next value but it hasn't been read
                              // yet (is set to null when read.)
      return true;
    if (!iter.hasNext())
      return false;
    possibleNext = iter.next();
    if (!predicate.test(possibleNext)) {
      possibleNext = null;
      return hasNext();
    }
    // possibleNext is now certainly the next element (it's passed the predicate
    // test)
    return true;
  }

  @Override
  public T next() {
    if (possibleNext == null && !hasNext())
      throw new NoSuchElementException("There is no more element matching the predicate");
    T copyOfPossibleNext = possibleNext;
    possibleNext = null; // reset possible next to null so it can't be read twice/next doesn't return the
                         // same value twice.
    return copyOfPossibleNext;
  }
}
