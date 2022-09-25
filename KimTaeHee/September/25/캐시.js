function solution(cacheSize, cities) {
    let answer = 0;
    
    for (let i = 0; i < cities.length; i++) {
        cities[i] = cities[i].toUpperCase();
    }
    
    let cache = [];
    
    for (let i = 0; i < cities.length; i++) {
        if (cache.length < cacheSize) {
            if (cache.includes(cities[i])) {
                answer += 1;
                cache = [...cache.filter(element => element !== cities[i]), cities[i]];
            } else {
                cache.push(cities[i]);
                answer += 5;
            }
        } else {
            if (cache.includes(cities[i])) {
                answer += 1;
                cache = [...cache.filter(element => element !== cities[i]), cities[i]];
            } else {
                    cache.shift();
                
                if (cache.length < cacheSize) {
                cache.push(cities[i]);                   
                    
                }
                answer += 5;
            }
            
        }
    }
    
    return answer;
}
