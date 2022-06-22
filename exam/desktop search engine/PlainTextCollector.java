import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

public class PlainTextCollector implements KeywordCollector {

  @Override
  public Set<String> getKeywords(Resource res) {
    Objects.requireNonNull(res, "res must be non-null");
    HashSet<String> keywordsSet = new HashSet<String>();
    new TextFileIterator(res).forEachRemaining(keywordsSet::add);
    return keywordsSet;
  }

}