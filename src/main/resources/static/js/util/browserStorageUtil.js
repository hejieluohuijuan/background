var BrowserStorage=function(){
    /**
     *添加localStorage
     */
    function addLocalStorage(name, value) {
        localStorage.setItem(name, value);
    }
    /**
     *获取cookie
     */
    function getLocalStorage(name) {
        if (localStorage.getItem(name) == null) {
            return "";
        } else {
            return localStorage.getItem(name);
        }
    }
    return{
        addLocalStorage:function(name, value){
            addLocalStorage(name, value);
        },
        getLocalStorage:function(name){
            return getLocalStorage(name);
        },
    }
}();