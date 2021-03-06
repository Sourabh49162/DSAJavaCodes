class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int transformations = 0;
        if(!wordList.contains(endWord)){
            return transformations;
        }
        if(wordList.contains(beginWord)){
            wordList.remove(beginWord);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while(queue.size() != 0){
            int level = queue.size();
            transformations++;
            while(level > 0){
                String word = queue.poll();
                if(word.equals(endWord)){
                    return transformations;
                }
                for(int i=0; i<word.length(); i++){
                    for(char ch='a'; ch<='z'; ch++){
                        String newWord = word.substring(0,i)+ch+word.substring(i+1);
                        if(wordList.contains(newWord)){
                            queue.add(newWord);
                            wordList.remove(newWord);
                        }
                    }
                }
                level--;
            }
        }
        return 0;        
    }
}