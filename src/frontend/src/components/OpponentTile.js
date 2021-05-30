import React from 'react';
import { Link } from 'react-router-dom';
import { TeamLogo } from './TeamLogo';
import './OpponentTile.scss'

export const OpponentTile = ({match, teamName}) => {

    const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;
    const result = opponentTeam !== match.winner;
    const isMatchTied = match.result === 'tie';

    return (
        <div className={result ?
         `${opponentTeam.toLowerCase().split(' ')[0]} opponentTile matchWon` :
          `${opponentTeam.toLowerCase().split(' ')[0]} opponentTile matchLost`}>
            <span className="vs">vs </span>
            <div className="few-details">
                <h2 className="opponent-team-name">
                    <Link to={`/${opponentTeam}`}>{opponentTeam}</Link>
                </h2>
                {isMatchTied ? <h3>Match was a Draw</h3> :
                <h3 className="winner">{match.winner} won by {match.resultMargin} {match.result}.</h3>
                }
            </div>
            <Link to={`/${opponentTeam}`}>
                <TeamLogo teamName={opponentTeam} />
            </Link>
        </div>
    );
}