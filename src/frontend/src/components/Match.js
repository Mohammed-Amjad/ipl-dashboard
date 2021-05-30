import { React, useState } from 'react';
import { FullMatchDetails } from './FullMatchDetails';
import { OpponentTile } from './OpponentTile';
import './Match.scss';

export const Match = ({ match, teamName }) => {

    const [isClicked, setClick] = useState(false);

    const onClick = () => { 
        setClick(!isClicked);
    }

    const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;

    return (
        <div className={`Match ${opponentTeam.toLowerCase().split(' ')[0]}`} onClick={onClick} >
            {isClicked ? <FullMatchDetails match={match} teamName={teamName} />
                : <div>
                    <OpponentTile match={match} teamName={teamName} />
                    <h5 className="view-match">View Match -></h5>
                </div>}
        </div>
    );
}