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

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Using class adapter pattern. Can be changed to use object adapter pattern
 * instead.
 */
public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {

  @Override
  public boolean addCatalogEntry(Event e, Set<Time> tSet) {
    Objects.requireNonNull(e, "e must be non-null");
    Objects.requireNonNull(tSet, "tSet must be non-null");
    for (Time t : tSet) {
      Objects.requireNonNull(t, "Each element in tSet must be non-null");
    }

    if (this.containsKey(e))
      return false;
    this.put(e, tSet);
    return true;
  }

  @Override
  public boolean addTimeToEvent(Event e, Time t) {
    Objects.requireNonNull(e, "e must be non-null");
    Objects.requireNonNull(t, "t must be non-null");
    Set<Time> timeSet = this.get(e);
    if (timeSet == null || timeSet.contains(t))
      return false;
    timeSet.add(t);
    return true;
  }

  @Override
  public Set<Event> getAllEvents() {
    return this.keySet();
  }

  @Override
  public Set<Time> getTimesOfEvent(Event e) {
    Objects.requireNonNull(e, "e must be non-null");
    return this.get(e);
  }

  @Override
  public Map<Event, Set<Time>> filterByEventCategory(EventCategory category) {
    Objects.requireNonNull(category, "category must be non-null");
    return this.entrySet().stream().filter(entry -> entry.getKey().getCategory() == category)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @Override
  public Set<Time> deleteEvent(Event e) {
    Objects.requireNonNull(e, "e must be non-null");
    return this.remove(e);
  }

  @Override
  public boolean deleteTime(Event e, Time t) {
    Objects.requireNonNull(e, "e must be non-null");
    Objects.requireNonNull(t, "t must be non-null");
    Set<Time> timeSet = this.get(e);
    if (timeSet == null)
      return false;
    return timeSet.remove(t);
  }

}