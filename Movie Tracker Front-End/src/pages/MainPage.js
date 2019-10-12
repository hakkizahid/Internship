import React from 'react';
import {Link} from 'react-router-dom';
import "../pagesCss/MainPage.css";

class MainPage extends React.Component{
    render(){
        return(
            <div className= "outerDiv2">
                <div className ="headerDiv">
                    <span className="mainTitle">~MAIN PAGE~</span>
                </div>
                <div>
                    <Link className = "linker" to="./movies">Movies</Link>
                    <br/>
                    <Link className = "linker" to="./directors">Directors</Link>
                    <br/>
                    <Link className = "linker" to="./users">Users</Link>
                    <br/>
                    <Link className = "linker" to="/movielist">Lists</Link>
                    <br/>
                </div>
               
            </div>
        )
    }
}

export default MainPage;