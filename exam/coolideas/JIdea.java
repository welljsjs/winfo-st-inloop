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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JIdea extends JContent {
  private JState state;
  private List<JAttachment> attachments = new ArrayList<JAttachment>();
  private final JState DRAFT_STATE = new Draft();
  private final JState OPEN_DRAFT_STATE = new OpenDraft();
  private final JState APPROVED_IDEA_STATE = new ApprovedIdea();
  private final JState RELEASED_IDEA_STATE = new ReleasedIdea();
  private final JState DECLINED_IDEA_STATE = new DeclinedIdea();

  public JIdea(String title, String description) {
    super(title, description);
    state = DRAFT_STATE; // set initial state.
  }

  public void discuss(String text) {
    Objects.requireNonNull(text, "text must be non-null");
    if (text.isEmpty())
      throw new IllegalArgumentException("text must be non-empty");
    state.discuss(text);
  }

  public void evaluate(JValuation valuation) {
    Objects.requireNonNull(valuation, "valuation must be non-null");
    state.evaluate(valuation);
  }

  public void hold() {
    state.hold();
  }

  public void release() {
    state.release();
  }

  public void decline() {
    state.decline();
  }

  public boolean isDeclined() {
    return state == DECLINED_IDEA_STATE;
  }

  public boolean isReleased() {
    return state == RELEASED_IDEA_STATE;
  }

  public String getCurrentDiscussion() {
    return state.getCurrentDiscussion();
  }

  public JValuation getValuation() {
    return state.getValuation();
  }

  public void addAttachment(JAttachment attachment) {
    Objects.requireNonNull(attachment, "attachment must be non-null");
    attachments.add(attachment);
  }

  public List<JAttachment> getAttachments() {
    return attachments;
  }

  public boolean removeAttachment(JAttachment attachment) {
    Objects.requireNonNull(attachment, "attachment must be non-null");
    return attachments.remove(attachment);
  }

  @Override
  public int hashCode() {
    return getTitle().hashCode();
  }

  // @Override
  // public boolean equals(Object obj) {
  // if (!(obj instanceof JIdea))
  // throw new IllegalArgumentException("obj must be an instance of JIdea to
  // compare");
  // return getTitle().equals(((JIdea) obj).getTitle());
  // }

  public abstract class JState {
    private String currentDiscussion;
    private JValuation valuation;

    public void discuss(String text) {
      Objects.requireNonNull(text, "text must be non-null");
      if (text.isEmpty())
        throw new IllegalArgumentException("text must be non-empty");
      throw new IllegalStateException();
    }

    public void evaluate(JValuation valuation) {
      Objects.requireNonNull(valuation, "valuation must be non-null");
      throw new IllegalStateException();
    }

    public void hold() {
      throw new IllegalStateException();
    }

    public void release() {
      throw new IllegalStateException();
    }

    public void decline() {
      throw new IllegalStateException();
    }

    public String getCurrentDiscussion() {
      return currentDiscussion;
    }

    public void setCurrentDiscussion(String currentDiscussion) {
      Objects.requireNonNull(currentDiscussion, "currentDiscussion must be non-null");
      if (currentDiscussion.isEmpty())
        throw new IllegalArgumentException("currentDiscussion must be non-empty");
      this.currentDiscussion = currentDiscussion;
    }

    public JValuation getValuation() {
      return valuation;
    }

    public void setValuation(JValuation valuation) {
      this.valuation = Objects.requireNonNull(valuation, "valuation must be non-null");
    }
  }

  public class Draft extends JState {
    @Override
    public void hold() {
      state = OPEN_DRAFT_STATE;
    }

    @Override
    public void decline() {
      state = DECLINED_IDEA_STATE;
    }
  }

  public class OpenDraft extends JState {
    @Override
    public void discuss(String text) {
      Objects.requireNonNull(text, "text must be non-null");
      if (text.isEmpty())
        throw new IllegalArgumentException("text must be non-empty");
      String currentDiscussion = getCurrentDiscussion();
      setCurrentDiscussion(
          (currentDiscussion == null || currentDiscussion.isEmpty() ? text : currentDiscussion + text) + "\n");
    }

    @Override
    public void evaluate(JValuation valuation) {
      Objects.requireNonNull(valuation, "valuation must be non-null");
      setValuation(valuation);
    }

    @Override
    public void hold() {
      state = APPROVED_IDEA_STATE;
    }

    @Override
    public void decline() {
      state = DECLINED_IDEA_STATE;
    }
  }

  public class ApprovedIdea extends JState {
    @Override
    public void release() {
      state = RELEASED_IDEA_STATE;
    }
  }

  public class ReleasedIdea extends JState {
  }

  public class DeclinedIdea extends JState {
  }

}
