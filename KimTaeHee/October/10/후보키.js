function solution(relation) {
    var answer = 0;
    const setCount = relation[0].length;
    const setArray = [];
    for(let i = 0; i < setCount; i++) {
        setArray.push(new Set());
    }
    for (let i = 0; i < setCount; i++) {
        for (let j = 0; j < setArray.length; j++) {
            setArray[i].add(relation[j][i]);
        }
        
    }
    return answer;
}
