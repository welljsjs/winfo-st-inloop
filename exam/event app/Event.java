import java.util.Objects;

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

/**
 * An Event is defined by its title and an EventCategory which can be either an
 * EXHIBITION, a PRESENTATION or a SHOW. Additionally, Event implements
 * `Comparable<<Event>>`.
 * Events can be compared to other Events by their title, and if the title is
 * equal, by their category.
 */
public class Event implements Comparable<Event> {
  private String title;
  private EventCategory category;

  public Event(String title, EventCategory category) {
    Objects.requireNonNull(title, "title must be non-null");
    Objects.requireNonNull(category, "category must be non-null");
    if (title.isEmpty())
      throw new IllegalArgumentException("title must be non-empty");

    this.title = title;
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public EventCategory getCategory() {
    return category;
  }

  @Override
  public int compareTo(Event o) {
    Objects.requireNonNull(o, "o must be non-null");
    int titleComparisonResult = title.compareTo(o.title);
    return titleComparisonResult == 0 ? (category.compareTo(o.category)) : titleComparisonResult;
  }
}