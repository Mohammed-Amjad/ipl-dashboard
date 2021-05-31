import React from 'react';
import { Link } from 'react-router-dom';
import './TeamPageHeading.scss';
import { TeamLogo } from './TeamLogo';

export const TeamPageHeading = ({ team }) => {

    const { teamName, lastPlayedOn, totalMatches, matchesWon, matchesDrawn } = team;
    const matchesLost = totalMatches - matchesWon - matchesDrawn;
    if (!teamName || !lastPlayedOn) return null;

    return (
        <div className={`TeamPageHeading ${team.teamName.toLowerCase().split(' ')[0]}`}>
            <TeamLogo teamName={teamName} />
            <div className="team-details">
                <h1 className="teamName">{teamName}</h1>
                <div className="extra-details">
                    <p>Total Matches: {totalMatches}</p>
                    <p>Matches won: {matchesWon}</p>
                    <p>Matches Lost: {matchesLost}</p>
                    <p>Matches drawn: {matchesDrawn}</p>
                </div>
                <div className="more-link">
                    <Link to={`/${teamName}/matches/${lastPlayedOn.slice(0, 4)}`}>
                        <h4>View all {`->`}</h4>
                    </Link>
                </div>
            </div>
        </div>
    );
}