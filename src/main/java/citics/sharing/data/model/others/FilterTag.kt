package citics.sharing.data.model.others

/**
 * Save the data for each tag when filtering MY ASSET such as
 * type (nha dat, can ho, ...), kind (dang ban, da ban, ...),
 * area (100m2,...), price (1ty-2ty,...), ...
 */
class FilterTag {
    //Label on screen
    var title : String = ""
    //The key for API
    var key : String = ""
    //Number of results for this tag
    var number : Int? = 0
    //TRUE if user selected this tag
    var isSelected : Boolean = false
    //TRUE if user can click to delete this tag out of list
    var canDelete : Boolean = false
    //TRUE if this tag is main (is a category)
    var isMainTag : Boolean = true

    constructor(key : String, title : String){
        this.key = key
        this.title = title
        this.isMainTag = true
        this.canDelete = false
    }

    constructor(key : String, title : String, mainTag : Boolean) : this(key, title){
        this.isMainTag = mainTag
        this.canDelete = !mainTag
    }

    override fun toString(): String {
        return "{"+key+", "+title+", "+number+", "+isMainTag+", "+canDelete+", "+isSelected+"}"
    }
}