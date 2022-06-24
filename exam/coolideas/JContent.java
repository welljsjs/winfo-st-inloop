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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class JContent {
  private String title;
  private String description;
  private Set<ContentObserver> observers = new HashSet<ContentObserver>();

  public JContent(String title, String description) {
    Objects.requireNonNull(title, "title must be non-null");
    Objects.requireNonNull(description, "description must be non-null");
    if (title.isEmpty() || description.isEmpty())
      throw new IllegalArgumentException("title and description must be non-empty");
    this.title = title;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    Objects.requireNonNull(description, "description must be non-null");
    if (description.isEmpty())
      throw new IllegalArgumentException("description must be non-empty");
    this.description = description;
    observers.stream().forEach(observer -> observer.update(this));
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    Objects.requireNonNull(title, "title must be non-null");
    if (title.isEmpty())
      throw new IllegalArgumentException("title must be non-empty");
    this.title = title;
    observers.stream().forEach(observer -> observer.update(this));
  }

  public void addObserver(ContentObserver observer) {
    Objects.requireNonNull(observer, "observer must be non-null");
    observers.add(observer);
  }

  public void removeObserver(ContentObserver observer) {
    Objects.requireNonNull(observer, "observer must be non-null");
    observers.remove(observer);
  }

  public int countObservers() {
    return observers.size();
  }

  @Override
  public String toString() {
    return "" + getClass().getSimpleName().replaceFirst("^J", "") + ": " + title + "\n" + description;
  }
}
