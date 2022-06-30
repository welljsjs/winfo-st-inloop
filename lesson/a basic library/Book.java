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

public class Book {
  private String title;

  public Book(String title) {
    if (title == null || title.isEmpty())
      throw new IllegalArgumentException("title must be non-null and non-empty");
    this.title = title;
    System.out.println("Book " + title + " created.");
  }

  @Override
  public String toString() {
    return title;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public int hashCode() {
    return title.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Book))
      throw new IllegalArgumentException("obj must be an instance of book to compare");
    return this.title.equals(((Book) obj).title);
  }
}
