import java.util.Iterator;

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

public interface Matrix<T> {
  public int getRowCount();

  public int getColumnCount();

  public int getObjectCount();

  public int getDistinctObjectCount();

  public Iterator<T> iterator();

  public T get(int row, int column);

  public T put(int row, int column, T value);

  public boolean contains(T value);
}
