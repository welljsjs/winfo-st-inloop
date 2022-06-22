import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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

public class TextFileIterator implements Iterator<String> {
  private final String resourceString;
  private final List<String> words;
  private final Iterator<String> wordsIterator;

  public TextFileIterator(Resource res) {
    Objects.requireNonNull(res, "res must be non-null");
    resourceString = getAsString(res);
    words = Arrays.asList(resourceString.replaceAll("-?\\n", "").split("[^a-zA-Z]+"));
    wordsIterator = words.iterator();
  }

  @Override
  public boolean hasNext() {
    return wordsIterator.hasNext();
  }

  @Override
  public String next() {
    return wordsIterator.next();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("remove is not supported on this iterator");
  }

  public String getAsString(Resource res) {
    return "We wish you good luck in this exam!\nWe hope you are well pre-\npared.";
  }

}