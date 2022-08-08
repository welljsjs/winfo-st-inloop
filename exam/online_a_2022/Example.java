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

/* Diese Datei wird nicht bewertet. Nutzen Sie sie gerne zum Testen. */

import java.util.*;

public class Example {
    public static void main(String[] args) {
        Iterable<String> names = List.of("Hermine", "Harry", "Ronald", "Hagrid", "Albus", "Ginny");
        EasyIterator<String> iterator = new EasyIteratorImpl<String>(names);
        // zum Testen auskommentieren:
        iterator = new WithLimitIterator<String>(iterator, 0);

        while (iterator.moveNext()) {
            System.out.println(iterator.current());
            // current() könnte beliebig oft aufgerufen werden, ohne den Iterator zu ändern
        }

        // Nach der Iteration bleibt das letzte Element als current() erhalten:
        System.out.println("iterator.current() nach der Iteration: " + iterator.current());

        // Cheers!
    }
}
