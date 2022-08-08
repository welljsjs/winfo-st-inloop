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

import java.util.*;

public class EasyIteratorImpl<E> implements EasyIterator<E> {

    // Attribute
    private E current;
    private Iterator<E> adapted;

    // Konstruktor
    public EasyIteratorImpl(Iterable<E> iterable) {
        adapted = iterable.iterator();
    }

    @Override
    public E current() {
        return current;
    }

    @Override
    public boolean moveNext() {
        if (!adapted.hasNext())
            return false;
        current = adapted.next();
        return true;
    }

}
