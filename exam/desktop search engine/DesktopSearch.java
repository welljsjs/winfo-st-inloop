import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

public class DesktopSearch {
  private Map<String, ResourceType> types = new HashMap<String, ResourceType>();
  private Index index = new Index();

  public void registerType(String extension, ResourceType type) {
    Objects.requireNonNull(extension, "extension must be non-null");
    Objects.requireNonNull(type, "type must be non-null");
    types.put(extension, type);
  }

  public ResourceType getType(String extension) {
    if (extension == null)
      return null;
    return types.get(extension);
  }

  public void unregisterType(String extension) {
    Objects.requireNonNull(extension, "extension must be non-null");
    if (extension.isEmpty())
      throw new IllegalArgumentException("extension must be non-empty");
    types.remove(extension);
  }

  public void registerResource(Resource res) {
    Objects.requireNonNull(res, "res must be non-null");
    index.add(res);
  }

  public List<Resource> processRequest(String request) {
    Objects.requireNonNull(request, "request must be non-null");
    if (request.isEmpty())
      throw new IllegalArgumentException("request must be non-empty");
    return index.getResources(request);
  }
}