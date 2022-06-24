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

public class JMember implements ContentObserver {
  Set<JTopic> topics = new HashSet<JTopic>();

  public void subscribe(JTopic topic) {
    Objects.requireNonNull(topic, "topic must be non-null");
    topics.add(topic);
    topic.addObserver(this);
  }

  public void unsubscribe(JTopic topic) {
    Objects.requireNonNull(topic, "topic must be non-null");
    topics.remove(topic);
    topic.removeObserver(this);
  }

  @Override
  public void update(JContent content) {
    Objects.requireNonNull(content, "content must be non-null");
    if (!(content instanceof JTopic))
      throw new IllegalArgumentException("content must be an instance of JTopic (subclass of JContent)");
    System.out.println("The topic " + ((JTopic) content).getId() + " has been updated!");
  }

  public Set<JTopic> getSubscribedTopics() {
    return topics;
  }
}
