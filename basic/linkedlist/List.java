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

public class List {
  private ListElement head;

  public void append(String content) {
    ListElement newElement = new ListElement(content);
    if (head == null) {
      head = newElement;
      return;
    }
    ListElement lastElement = head;
    while (lastElement.getNext() != null)
      lastElement = lastElement.getNext();
    lastElement.setNext(newElement);
  }

  public String remove(String content) {
    if (content == null)
      throw new IllegalArgumentException("content must be non-null");
    if (content.isEmpty())
      throw new IllegalArgumentException("content must be non-empty");

    for (ListElement currEl = head, prev = null; currEl != null; prev = currEl, currEl = currEl.getNext())
      if (currEl.getContent().equals(content)) {
        if (prev != null)
          prev.setNext(currEl.getNext());
        else
          head = currEl.getNext();
        return currEl.getContent();
      }

    return null;
  }
}