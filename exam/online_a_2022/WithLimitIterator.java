// Copyright 2022 Julius Schmidt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class WithLimitIterator<E> extends IteratorWrapper<E> {

  // Attribute
  private int limit;
  private int counter;

  // Konstruktor
  public WithLimitIterator(EasyIterator<E> wrapped, int limit) {
    super(wrapped);
    this.limit = limit;
  }

  @Override
  public boolean moveNext() {
    if (counter >= limit)
      return false;
    if (super.moveNext()) {
      ++counter;
      return true;
    }
    return false;
  }

}
