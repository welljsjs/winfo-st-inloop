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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class JIdeaPool {
  private Map<JTopic, Set<JIdea>> pool = new HashMap<JTopic, Set<JIdea>>();

  public void add(JTopic topic) {
    Objects.requireNonNull(topic, "topic must be non-null");
    pool.putIfAbsent(topic, new HashSet<JIdea>());
  }

  public void add(JIdea idea, JTopic topic) {
    Objects.requireNonNull(idea, "idea must be non-null");
    Objects.requireNonNull(topic, "topic must be non-null");

    if (pool.values().stream().flatMap(Set::stream).filter(i -> idea.equals(i) && idea != i).count() > 0)
      return;
    Set<JIdea> oldSet;
    if ((oldSet = pool.putIfAbsent(topic, new HashSet<JIdea>(Arrays.asList(idea)))) != null)
      oldSet.add(idea);
  }

  public boolean remove(JTopic topic) {
    Objects.requireNonNull(topic, "topic must be non-null");
    return pool.remove(topic) != null;
  }

  public boolean remove(JIdea idea) {
    Objects.requireNonNull(idea, "idea must be non-null");
    return pool.values().stream().flatMapToInt(hashSet -> IntStream.of(hashSet.remove(idea) ? 1 : 0)).sum() > 0;
  }

  public JIdea getIdea(String title) {
    Objects.requireNonNull(title, "title must be non-null");
    if (title.isEmpty())
      throw new IllegalArgumentException("title must be non-empty");
    return pool.values().stream().flatMap(Set::stream).filter(idea -> idea.getTitle().equals(title)).findAny()
        .orElse(null);
  }

  public int numberOfTopics() {
    return pool.size();
  }

  public int numberOfIdeas() {
    return pool.values().stream().flatMap(ideaSet -> ideaSet.stream()).distinct().toArray().length; // turn sets into
                                                                                                    // streams,
    // flatten and retrieve distinct
    // elements.
  }

  public void removeDeclined() {
    pool.values().stream().forEach(ideaSet -> ideaSet.removeIf(JIdea::isDeclined));
  }

  public void removeReleased() {
    pool.values().stream().forEach(ideaSet -> ideaSet.removeIf(JIdea::isReleased));
  }
}